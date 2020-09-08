public class Test 
{
	public static void main(String[] args)
	{
		Train one = new Train(null,null,12340,"Port Jeff",Integer.parseInt("0000"),15);
		Train two = new Train(null,null,234,"Penn Station",Integer.parseInt("0025"),15);
		Train three = new Train(null,null,2234,"Woodside",Integer.parseInt("0100"),15);
		Track oneTrack = new Track(null,null,null,null,null,0.0,1);
		oneTrack.addTrain(one);
		oneTrack.addTrain(two);
		oneTrack.addTrain(three);
		System.out.println(oneTrack.toString());
	}

}

