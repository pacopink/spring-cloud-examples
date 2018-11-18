package com.neo.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sys_user")
public class SysUser implements Serializable {
    @Id
    private String name;
    @Column
    private Integer age;
    @Column
    @Convert(converter=TypeConverter.class)
    private Type type;

    public SysUser() {
    }

    public SysUser(String name, Integer age, Type type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }
}
