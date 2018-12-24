package io.github.zhaoqi99.snnu_android.Model;

import java.util.List;

public class CourseModel {
    private String msg;
    private int status;
    private List<Data> data;

    public CourseModel(){}


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;

    }
    public class Data{

        private String attributes;
        private double credits;
        private String id;
        private List<Info> info;
        private String name;
        private String number;
        private String status;
        private String teacher;
        public void setAttributes(String attributes) {
            this.attributes = attributes;
        }
        public String getAttributes() {
            return attributes;
        }

        public void setCredits(double credits) {
            this.credits = credits;
        }
        public double getCredits() {
            return credits;
        }

        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setInfo(List<Info> info) {
            this.info = info;
        }
        public List<Info> getInfo() {
            return info;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setNumber(String number) {
            this.number = number;
        }
        public String getNumber() {
            return number;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }
        public String getTeacher() {
            return teacher;
        }

    }
    public class Info {

        private String buildings;
        private String campus;
        private String day;
        private String numOfClass;
        private String room;
        private String timeOfClass;
        private String week;
        public void setBuildings(String buildings) {
            this.buildings = buildings;
        }
        public String getBuildings() {
            return buildings;
        }

        public void setCampus(String campus) {
            this.campus = campus;
        }
        public String getCampus() {
            return campus;
        }

        public void setDay(String day) {
            this.day = day;
        }
        public String getDay() {
            return day;
        }

        public void setNumOfClass(String numOfClass) {
            this.numOfClass = numOfClass;
        }
        public String getNumOfClass() {
            return numOfClass;
        }

        public void setRoom(String room) {
            this.room = room;
        }
        public String getRoom() {
            return room;
        }

        public void setTimeOfClass(String timeOfClass) {
            this.timeOfClass = timeOfClass;
        }
        public String getTimeOfClass() {
            return timeOfClass;
        }

        public void setWeek(String week) {
            this.week = week;
        }
        public String getWeek() {
            return week;
        }

    }
}

