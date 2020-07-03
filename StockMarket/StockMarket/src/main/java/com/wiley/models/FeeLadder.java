package com.wiley.models;

public class FeeLadder {
	public int calculateFeeLadder(int noOfShares,int sharePrice)
	{
		if(noOfShares<=25)
		{
			return (int)noOfShares*sharePrice*2/100; 
		}
		else if(noOfShares>25 && noOfShares<=50)
		{
			return (int)noOfShares*sharePrice*2/100;
		}
		else if(noOfShares>50 && noOfShares<=100)
		{
			return (int)noOfShares*sharePrice*4/100;
		}
		else if(noOfShares>100&& noOfShares<=200)
		{
			return (int)noOfShares*sharePrice*5/100;
		}
		else if(noOfShares>200)
		{
			return (int)noOfShares*sharePrice*8/100;
		}
		else
		{
			return (int)noOfShares*sharePrice*15/100;
		}
	}

}

