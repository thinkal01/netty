package com.note.netty.ex07_thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import thrift.generated.PersonService;

public class ThriftServer {
    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args args1 = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(new PersonServiceImpl());
        // 压缩协议
        args1.protocolFactory(new TCompactProtocol.Factory());
        // 传输层
        args1.transportFactory(new TFramedTransport.Factory());
        args1.processorFactory(new TProcessorFactory(processor));
        // 半同步,半异步
        TServer server = new THsHaServer(args1);
        System.out.println("thrift server start!");
        server.serve();
    }
}
