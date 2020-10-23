# spring-cloud-bus-rsocket-sample
Sample app that uses spring-cloud-bus with RSocket Routing as the transport.

1. Clone https://github.com/spencergibb/rsocket-routing-sample and run `BrokerApplication`
2. Run `DemobusApplication`
3. Run `DemobusApplication --spring.profiles.active=bus2`
4. Run `http POST :8080/actuator/busrefresh` or `http POST :8080/actuator/busrefresh/service_name=busclient`. You should see something like the following in the `DemobusApplication` output 
```
2020-10-23 15:23:51.548 DEBUG 206569 --- [tor-tcp-epoll-2] o.springframework.cloud.bus.BusConsumer  : Received remote event from bus: [RefreshRemoteApplicationEvent@12fd361c id = '14d10461-a197-4773-95ac-9030a59cd96d', originService = 'busclient:busclient-1', destinationService = 'bus=true']
2020-10-23 15:23:51.548  INFO 206569 --- [tor-tcp-epoll-2] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request.
2020-10-23 15:23:51.659  INFO 206569 --- [tor-tcp-epoll-2] o.s.cloud.bus.event.RefreshListener      : Keys refreshed []
```

The selector after `/busrefresh` is semicolon `/` delimited name=value pairs. So `/actuator/busrefresh/service_name=busclient/instance_name=busclient-2` would match client two.
In busclient-1 you would see something like
```
2020-10-23 16:06:50.810  INFO 214362 --- [nio-8080-exec-1] o.s.cloud.bus.event.RefreshListener      : Refresh not performed, the event was targeting bus=true;service_name=busclient;instance_name=busclient-2
``` 
while busclient-2 would see the received messages.
