package com.example.demo.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @Column(name = "student_id")
    private Long studentId; // 这个ID与User的userId相同
    
    @Column(name = "student_name")
    private String studentName;
    
    @Column(name = "rest_time")
    private String restTime;
    
    @Column(name = "faculty")
    private String faculty;
    
    @Column(name = "bed_number")
    private String bedNumber;
    
    @Column(name = "contact_number")
    private String contactNumber;
    
    @Column(name = "introduction", columnDefinition = "TEXT") // 长文本
    private String introduction;
    
    @Column(name = "image_path")
    private String imagePath;
    
    // 一对一：学生对应一个用户账号
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    @MapsId // 共享主键
    private User user;
    
    // 多对一：多个学生属于一个宿舍
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    
    // 一对多：一个学生可以提交多个申请
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<RoomApplication> applications = new ArrayList<>();
    
    // 一对多：一个学生可以收到多个评价
    @OneToMany(mappedBy = "evaluatedStudent", cascade = CascadeType.ALL)
    private List<StudentEvaluation> receivedEvaluations = new ArrayList<>();
    
    // 一对多：一个学生可以评价多个其他学生
    @OneToMany(mappedBy = "evaluator", cascade = CascadeType.ALL)
    private List<StudentEvaluation> givenEvaluations = new ArrayList<>();
    
    public Student() {
    }
    
    // Getter和Setter
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    
    public String getRestTime() { return restTime; }
    public void setRestTime(String restTime) { this.restTime = restTime; }
    
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    
    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
    
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    
    public List<RoomApplication> getApplications() { return applications; }
    public void setApplications(List<RoomApplication> applications) { this.applications = applications; }
    
    public List<StudentEvaluation> getReceivedEvaluations() { return receivedEvaluations; }
    public void setReceivedEvaluations(List<StudentEvaluation> receivedEvaluations) { this.receivedEvaluations = receivedEvaluations; }
    
    public List<StudentEvaluation> getGivenEvaluations() { return givenEvaluations; }
    public void setGivenEvaluations(List<StudentEvaluation> givenEvaluations) { this.givenEvaluations = givenEvaluations; }
}