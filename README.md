# Phased Test Demo
This is a demo for the Phased Testing project [Phased Testing](https://github.com/adobe/phased-testing), which is a framework that allows users to properly test the effect of system changes. 

The project is a very simple program that includes a system update.

Word of note: _This is a simple demo example, and is not a real web shop. The examples here are just there to show how Phased Testing with ork._

## Basic scenario
We assume that we have a simple web shop. The user will find a product, put it in their basket, and then checkout. Finally we make sure that the paid price is the same as what we browsed for.

## Demo Executions
In this chapter we will describe the use cases:

### 1. Normal Execution
In this scenario we will assume that this scenario is for the day to day normal executions.

Run the following command.

`mvn clean test`

#### Under the hub
For the demo, we wanted to show a simple process, so we do not perform the provisioning. This is solved because we have added the notion of @BeforePhase.
```
@BeforeSuite
@BeforePhase(appliesToPhases = { Phases.NON_PHASED})
public void resetDB() {
...
```

In this case the resetDB method is executed only when we are not in a Phase.


### 2. Normal System Change
In this scenario we simulate a real system update, where it goes well.

| Nr. | Action | Command |
|-----| ------ | ------- |
| 1   | Provision system | `bash provisionSystem.sh` |
| 2   | Run tests in PRODUCER mode | `mvn clean test -DPHASED.TESTS.PHASE=PRODUCER` |
| 3   | Make system change (non-breaking) | `bash updateSystem_normal.sh` | 
|  4  | Run tests in CONSUMER mode | `mvn clean test -DPHASED.TESTS.PHASE=CONSUMER` |

Expected outcome : Success

#### Under the hub
For the demo, we need to load the system before the test. This is solved because we have added the notion of @BeforePhase.

```
@BeforeTest
@BeforePhase
public void loadDB() {
...
```

In this case the resetDB method is executed only when we are not in a Phase.


### 3. System Change Negative Case
In this scenario we simulate a real system update, where the system change causes one of the phase groups to fail.

The update of the system introduces an unexpected price change. This will case on of the phase groups to fail (shuffle group 2_1).

| Nr. | Action | Command |
| -----| ------ | ------- |
|  1   | Provision system | `bash provisionSystem.sh` |
|  2   | Run tests in PRODUCER mode | `mvn clean test -DPHASED.TESTS.PHASE=PRODUCER` |
|  3   | Make system change (breaking) | `bash updateSystem_changePrice.sh` |
|  4   | Run tests in CONSUMER mode | `mvn clean test -DPHASED.TESTS.PHASE=CONSUMER` |

Expected outcome : Fail. We should get an error :
```
java.lang.AssertionError: We should have the same price as before expected [52] but found [56] [Failed at step : phased-shuffledGroup_2_1_step3_payForProduct - CONSUMER]
```

