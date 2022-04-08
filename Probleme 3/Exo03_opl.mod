/*********************************************
 * OPL 12.6.0.0 Model
 * Author: C A T E C H
 * Creation Date: 8 avr. 2022 at 13:08:02
 *********************************************/

 

{string} Cities =...;
{string} Products = ...;
float Capacity = ...;

float Supply[Products][Cities] = ...;
float Demand[Products][Cities] = ...;

/*assert
  forall(p in Products)
    sum(o in Cities) Supply[p][o] == sum(d in Cities) Demand[p][d];*/


dvar float+ Trans[Products][Cities][Cities];


maximize
  sum( p in Products , o , d in Cities ) 
     Trans[p][o][d];
   
subject to {
  forall( p in Products , o in Cities )
    ctSupply:  
      sum( d in Cities ) 
        Trans[p][o][d] == Supply[p][o];
  forall( p in Products , d in Cities ) 
    ctDemand:
      sum( o in Cities ) 
        Trans[p][o][d] == Demand[p][d];
   forall( o , d in Cities )
     ctCapacity:
       sum( p in Products ) 
         Trans[p][o][d] <= Capacity;
}  

execute DISPLAY {
  writeln("trans = ",Trans);
}
 