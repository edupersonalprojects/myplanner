package br.com.edugoncalves.myplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PlannerRecord {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private  String service;
   private  String customer; // TODO: fix me
   private  String location;
   private LocalDateTime dateTime;
   private boolean done;
   private  boolean canceled;
}
