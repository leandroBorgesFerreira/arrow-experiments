# arrow-experiments
Experimentation SDK created with Arrow

## Introduction	

This idea of this repository is to show how to create a SDK with Arrow and Tagless Final.

By making the Monad that the program runs generic, it is possible to change the run time that will be used to run the program without changings any code. 

## Modules
The modules of this project

### experiments-core
That's the actual SDK. It picks the experiments from the API and delivers. It is made used Tagless Final, so the monad is generic in the whole implentation of the module. The concretization is made in other modules. 

It uses Retrofit as a web client, parses JSOn using Moshi and uses arrow libraies in the implementation of the logic. 

### experiments-rx
This module does just one this: Concretizes the implementation of the `experiments-core` to RxJava (a `Single`). This module is created so uses of this SDK doesn't have to understand how Arrow works, they can simply include this library and use RxJava straight away.

### experiments-reactor
The same of `experiments-rx`, but the concrete type is the reactor code (a `Mono`).

### experiments-run
This module is not part of the SDK, this is only to see how the code works and to find examples of how to use it. 


