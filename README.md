Identified issues why spring boot application was not connecting to axon server: problem seems be running spring boot version above 3.1.5. When downgraded from 3.4.4 -> 3.1.5 the connection was successfull. 

Identified issues with Lombok annotation procession together with AggregateIdentifier, removed lombok third party dependency to set up classes with code instead.
