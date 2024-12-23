package visual_search;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: visual_search.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class VisualSearchGrpc {

  private VisualSearchGrpc() {}

  public static final java.lang.String SERVICE_NAME = "visual_search.VisualSearchService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<VisualSearchOuterClass.VisualSearchRequest,
      VisualSearchOuterClass.VisualSearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Search",
      requestType = VisualSearchOuterClass.VisualSearchRequest.class,
      responseType = VisualSearchOuterClass.VisualSearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<VisualSearchOuterClass.VisualSearchRequest,
      VisualSearchOuterClass.VisualSearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<VisualSearchOuterClass.VisualSearchRequest, VisualSearchOuterClass.VisualSearchResponse> getSearchMethod;
    if ((getSearchMethod = VisualSearchGrpc.getSearchMethod) == null) {
      synchronized (VisualSearchGrpc.class) {
        if ((getSearchMethod = VisualSearchGrpc.getSearchMethod) == null) {
          VisualSearchGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<VisualSearchOuterClass.VisualSearchRequest, VisualSearchOuterClass.VisualSearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  VisualSearchOuterClass.VisualSearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  VisualSearchOuterClass.VisualSearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VisualSearchServiceMethodDescriptorSupplier("Search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static VisualSearchServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VisualSearchServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VisualSearchServiceStub>() {
        @java.lang.Override
        public VisualSearchServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VisualSearchServiceStub(channel, callOptions);
        }
      };
    return VisualSearchServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static VisualSearchServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VisualSearchServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VisualSearchServiceBlockingStub>() {
        @java.lang.Override
        public VisualSearchServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VisualSearchServiceBlockingStub(channel, callOptions);
        }
      };
    return VisualSearchServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static VisualSearchServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VisualSearchServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VisualSearchServiceFutureStub>() {
        @java.lang.Override
        public VisualSearchServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VisualSearchServiceFutureStub(channel, callOptions);
        }
      };
    return VisualSearchServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void search(VisualSearchOuterClass.VisualSearchRequest request,
                        io.grpc.stub.StreamObserver<VisualSearchOuterClass.VisualSearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service VisualSearchService.
   */
  public static abstract class VisualSearchServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return VisualSearchGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service VisualSearchService.
   */
  public static final class VisualSearchServiceStub
      extends io.grpc.stub.AbstractAsyncStub<VisualSearchServiceStub> {
    private VisualSearchServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VisualSearchServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VisualSearchServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(VisualSearchOuterClass.VisualSearchRequest request,
                       io.grpc.stub.StreamObserver<VisualSearchOuterClass.VisualSearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service VisualSearchService.
   */
  public static final class VisualSearchServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<VisualSearchServiceBlockingStub> {
    private VisualSearchServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VisualSearchServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VisualSearchServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public VisualSearchOuterClass.VisualSearchResponse search(VisualSearchOuterClass.VisualSearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service VisualSearchService.
   */
  public static final class VisualSearchServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<VisualSearchServiceFutureStub> {
    private VisualSearchServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VisualSearchServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VisualSearchServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<VisualSearchOuterClass.VisualSearchResponse> search(
        VisualSearchOuterClass.VisualSearchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH:
          serviceImpl.search((VisualSearchOuterClass.VisualSearchRequest) request,
              (io.grpc.stub.StreamObserver<VisualSearchOuterClass.VisualSearchResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSearchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              VisualSearchOuterClass.VisualSearchRequest,
              VisualSearchOuterClass.VisualSearchResponse>(
                service, METHODID_SEARCH)))
        .build();
  }

  private static abstract class VisualSearchServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    VisualSearchServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return VisualSearchOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("VisualSearchService");
    }
  }

  private static final class VisualSearchServiceFileDescriptorSupplier
      extends VisualSearchServiceBaseDescriptorSupplier {
    VisualSearchServiceFileDescriptorSupplier() {}
  }

  private static final class VisualSearchServiceMethodDescriptorSupplier
      extends VisualSearchServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    VisualSearchServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (VisualSearchGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new VisualSearchServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
