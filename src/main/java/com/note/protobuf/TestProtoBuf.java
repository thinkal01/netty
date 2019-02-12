package com.note.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestProtoBuf {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("深圳").build();
        byte[] array = student.toByteArray();
        DataInfo.Student student1 = DataInfo.Student.parseFrom(array);
        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());
    }
}
