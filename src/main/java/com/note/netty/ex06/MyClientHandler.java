package com.note.netty.ex06;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class MyClientHandler extends SimpleChannelInboundHandler<DataInfo.MyMessage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int nextInt = new Random().nextInt(3);
        DataInfo.MyMessage myMessage = null;
        if (0 == nextInt) {
            myMessage = DataInfo.MyMessage.newBuilder().setDataType(DataInfo.MyMessage.DataType.StudentType).setStudent(DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("深圳").build()).build();
        } else if (1 == nextInt) {
            myMessage = DataInfo.MyMessage.newBuilder().setDataType(DataInfo.MyMessage.DataType.DogType).setDog(DataInfo.Dog.newBuilder().setName("小强").setAge(5).build()).build();
        } else {
            myMessage = DataInfo.MyMessage.newBuilder().setDataType(DataInfo.MyMessage.DataType.CatType).setCat(DataInfo.Cat.newBuilder().setName("小猫").setCity("深圳").build()).build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyMessage msg) throws Exception {

    }
}
