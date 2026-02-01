package br.com.edugoncalves.myplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PlannerRecord {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private  String service;
   private  String costumer;
   private  String location;
   private LocalDateTime dateTime;
   private boolean done;
   private  boolean canceled;
}
