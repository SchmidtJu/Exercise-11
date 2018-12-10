
# Event Sourcing

## Your work

Fork this project.

## 11.12.2018

1. Try to understand the actors from the sample project below.
2. Run the different actors and see how they work.
3. Run the http server with `sbt runMain de.innfactory.eventsourcing.Server` and try to get the `/` http endpoint. It should answer with Response 200.
4. Implement the IoT Actor as an persistenct actor. It should bunde all IoT Devicedata in an immutable data structure.
5. The Commands/Events should add a Temperatur or Preasure value for a given timestamp.
6. Expose the Endpoint for adding new data over http with akka-http as a post.

## 18.12.2018

1. Add rdbc (https://rdbc.io/) to your repo and run a local postgres container.
2. Add a [persistence query for leveldb](https://doc.akka.io/docs/akka/2.5/persistence-query-leveldb.html), which subscribes to all of your akka persistence events from last week
3. Try to write the data you'll get from the events to a materialized postgres view for each iot device.
4. Implement a route where you can get the data from your sql view.
X. (opt) try to run the iot Manager and ask the service over http if the event for a given id is valid


## Given Code

The Code is based on the akka persistence example by lightbends techub.

This tutorial contains examples that illustrate a subset of[Akka Persistence](http://doc.akka.io/docs/akka/2.5/scala/persistence.html) features.

- persistent actor
- persistent actor snapshots
- persistent actor recovery

Custom storage locations for the journal and snapshots can be defined in [application.conf](src/main/resources/application.conf).

### Persistent actor

[PersistentActorExample.scala](src/main/scala/sample/persistence/PersistentActorExample.scala) is described in detail in the [Event sourcing](http://doc.akka.io/docs/akka/2.5/scala/persistence.html#event-sourcing) section of the user documentation. With every application run, the `ExamplePersistentActor` is recovered from events stored in previous application runs, processes new commands, stores new events and snapshots and prints the current persistent actor state to `stdout`.

To run this example, type `sbt "runMain sample.persistence.PersistentActorExample"`.

### Persistent actor snapshots

[SnapshotExample.scala](src/main/scala/sample/persistence/SnapshotExample.scala) demonstrates how persistent actors can take snapshots of application state and recover from previously stored snapshots. Snapshots are offered to persistent actors at the beginning of recovery, before any messages (younger than the snapshot) are replayed.

To run this example, type `sbt "runMain sample.persistence.SnapshotExample"`. With every run, the state offered by the most recent snapshot is printed to `stdout`, followed by the updated state after sending new persistent messages to the persistent actor.

### Persistent actor recovery

[PersistentActorFailureExample.scala](src/main/scala/sample/persistence/PersistentActorFailureExample.scala) shows how a persistent actor can throw an exception, restart and restore the state by replaying the events.

To run this example, type `sbt "runMain sample.persistence.PersistentActorFailureExample"`.


___

akka-persistence by [innFactory](https://innfactory.de)

