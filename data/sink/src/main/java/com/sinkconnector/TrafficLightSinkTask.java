package com.sinkconnector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinkconnector.dataset.model.TrafficSignalData;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrafficLightSinkTask extends SinkTask {
  private static final Logger log = LoggerFactory.getLogger(TrafficLightSinkTask.class);
  private ObjectMapper objectMapper = new ObjectMapper(); // JSON 파서


  private com.sinkconnector.FirebaseAdmin firebaseAdmin;

  @Override
  public void start(Map<String, String> props) {
    String firebaseUrl = props.get("firebase.url");
    String firebaseConfig = props.get("firebase.config");

    try {
      firebaseAdmin = new FirebaseAdmin(firebaseUrl, firebaseConfig);
      firebaseAdmin.initializeFirebase();
    } catch (IOException e) {
      log.error("Failed to initialize Firebase", e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public void put(Collection<SinkRecord> records) {
    log.info("put 메소드 실행 Received {} records", records.size());
    for (SinkRecord record : records) {
      try {
        Object value = record.value();
        log.info("Record value: {}", value);
        if (value instanceof Map) {
          Map<String, Object> mapValue = (Map<String, Object>) value;
          String jsonString = objectMapper.writeValueAsString(mapValue);
          TrafficSignalData trafficSignalData = objectMapper.readValue(jsonString, TrafficSignalData.class);
          log.info("Parsed TrafficSignalData: {}", trafficSignalData);
          String id = String.valueOf(trafficSignalData.getItstId());
          if (id != null) {
            log.info("Saving data to Firebase: Topic = {}, ID = {}, Data = {}", record.topic(), id, trafficSignalData);
            firebaseAdmin.saveData(record.topic(), id, trafficSignalData);
            log.info("Data saved successfully to Firebase");
          }
        } else {
          log.warn("Record value is not a Map: {}", value.getClass());
        }
      } catch (Exception e) {
        log.error("Error processing record: {}", record, e);
      }
    }
  }

  @Override
  public void stop() {
  }

  @Override
  public String version() {
    return "1.0";
  }
}