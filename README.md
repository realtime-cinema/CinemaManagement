## About Application
Project is Cinema Management. The main feature is ordering seat feature with realtime. And System structure of its is micro-service( all services point to  one database). My team use Java just for API. And will use Nodejs for runtime.

### Tech used
Spring boot, Spring security, JWT
### Integration
VNPay e-wallet
### A little bit with ordering seat realtime feature
Most Applications's ordering seat is just a normal API, if two or more users at the same time request to order just same seat, if the person whose condition good than, they will have that seat, ortherwise that person will be in the queue constantly.
And myteam's solution is: if a person who pick before, that this seat will lock preventing to other request that want to pick it. if fail, just rollback, and the seat will be available.

## The database desgin 
LINK: https://dbdiagram.io/d/Trat-DJien-ITMC-Solution-65bf2fc6ac844320ae6458be
![image](https://github.com/realtime-cinema/CinemaManagement/assets/90248665/741cacde-2ff3-4207-885c-cf391ec005e5)
## Structure of Application
![image](https://github.com/realtime-cinema/CinemaManagement/assets/90248665/e361de87-dad8-4714-8981-593d135c5969)



## Progress
Done
