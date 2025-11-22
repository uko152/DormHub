package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Rooms")
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;
    
    @Column(name = "campus", nullable = false)
    private String campus;
    
    @Column(name = "building", nullable = false)
    private String building;
    
    @Column(name = "floor", nullable = false)
    private Integer floor;
    
    @Column(name = "room_number", nullable = false)
    private String roomNumber;
    
    @Column(name = "max_people", nullable = false)
    private Integer maxPeople;
    
    @Column(name = "room_type", nullable = false)
    private String roomType;
    
    @Column(name = "introduction", length = 500) // 指定长度
    private String introduction;
    
    @Column(name = "image_path")
    private String imagePath;
    
    // 一对多：一个宿舍有多个学生
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
    
    // 一对多：一个宿舍有多个评价
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<RoomEvaluation> evaluations = new ArrayList<>();
    
    public Room() {
    }
    
    public Room(String campus, String building, Integer floor, String roomNumber, 
                Integer maxPeople, String roomType) {
        this.campus = campus;
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.maxPeople = maxPeople;
        this.roomType = roomType;
    }
    
    // Getter和Setter
    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    
    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }
    
    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }
    
    public Integer getFloor() { return floor; }
    public void setFloor(Integer floor) { this.floor = floor; }
    
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    
    public Integer getMaxPeople() { return maxPeople; }
    public void setMaxPeople(Integer maxPeople) { this.maxPeople = maxPeople; }
    
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    
    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
    
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
    
    public List<RoomEvaluation> getEvaluations() { return evaluations; }
    public void setEvaluations(List<RoomEvaluation> evaluations) { this.evaluations = evaluations; }
}