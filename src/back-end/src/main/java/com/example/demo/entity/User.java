package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS") // 指定表名
public class User {
    
    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增
    @Column(name = "user_id") // 映射到表的user_id列
    private Long userId;
    
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING) // 枚举存储为字符串
    @Column(name = "user_type", nullable = false)
    private UserType userType;
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    // 必须有无参构造方法
    public User() {
    }
    
    // 有参构造方法（可选）
    public User(String userName, String password, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.createTime = LocalDateTime.now();
    }
    
    // Getter 和 Setter 方法
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public UserType getUserType() {
        return userType;
    }
    
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    
    // toString方法（调试用）
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                '}';
    }
}

// 枚举定义在单独的文件或同一个文件中
enum UserType {
    student, teacher, admin
}
