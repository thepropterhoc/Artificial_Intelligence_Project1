#Project 2 Flow Specification 
### Shelby Vanhooser, Nick Sparks  

___
___


## Part 1 : The High Level  

To begin, we separated the goals of our agressive agent into a series of distinct lobes of reasoning.  These included the following three categories: 

1. *Attack Weight* - How much do we want to attack another ship? 
2. *Energy Weight* - How much do we want to go get energy?
3. *Resource Weight* - How much do we want to go mine resources?

## Part 2 : The Biases


#### Energy

_Parameters_  

<table>
	<th>
		Type
	</th>
	<th>
		Name
	</th>
	<th>
		Description
	</th>
	<tr>
		<td>
			Toroidal2DPhysics
		</td>  
		<td>
			space
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
	<tr>
		<td>
			Ship
		</td>  
		<td>
			ship
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
</table>

_Method_

~~~java
private double getEnergyActionBias(Toroidal2DPhysics space, Ship ship ){ ... } 
~~~

_Implementation_

From the space, we will iterate over all the available `Beacon` objects to find each ranked in terms of its distance and amount of energy onboard.  

Then, of these, we will divide the energy onboard by the distance to give us an overall bias of how valuable this particular `Beacon` is to us, multiply by a scaling factor, and add a constant term (both the multiplying factor and additive term will be adjustable for future testing) 

In particular, lets take a `Beacon` _b_ and see what its weight would be computed as.  




--  

#### Attack  

_Parameters_

<table>
	<th>
		Type
	</th>
	<th>
		Name
	</th>
	<th>
		Description
	</th>
	<tr>
		<td>
			Toroidal2DPhysics
		</td>  
		<td>
			space
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
	<tr>
		<td>
			Ship
		</td>  
		<td>
			ship
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
</table>

_Method_

~~~ java
private double getAttackActionBias(Toroidal2DPhysics space, Ship ship ){ ... }
~~~

_Implementation_


--

#### Resources

_Parameters_

<table>
	<th>
		Type
	</th>
	<th>
		Name
	</th>
	<th>
		Description
	</th>
	<tr>
		<td>
			Toroidal2DPhysics
		</td>  
		<td>
			space
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
	<tr>
		<td>
			Ship
		</td>  
		<td>
			ship
		</td>
		<td>
			The space the actionable objects exist in 
		</td>
	</tr>
</table>

_Method_

~~~ java
private double getResourcesActionBias(Toroidal2DPhysics space, Ship ship ){ ... } 
~~~

_Implementation_


--

## Part 3 : The Actions
