package de.fhdw.bfwc322b.stardewCalc;

import java.util.Arrays;
import java.util.HashMap;

public class StardewCalc {
    public static void main(String[] args) {


        Material iron = new Material("Iron", 17, 211);
        Material gold = new Material("Gold", 5, 50);
        int[] out = new StardewCalc().smelt(new Material[]{iron, gold}, 80);
        System.out.println(Arrays.toString(out));
    }

    private int[] smelt(Material[] in, int coal){
        Material[] out = Arrays.copyOf(in, in.length);

        while(coal > 0){
            //if there is no input you can't smelt
            if(in[0] == null) break;
            //determine items that are able to be smelted, sort out those that aren't
            for(int i = 0; i < in.length; i++){
                //so it doesn't try to check for deleted cells
                if(in[i] == null) break;

                if(in[i].getRaw() <5){
                    for(int e = i+1; e < in.length; e++){
                        in[e-1] = in[e];
                    }
                    in[in.length-1] = null;
                }
            }
            //if there is no input after we clear input you can't smelt, you can't smelt either
            if(in[0] == null){
                break;
            }
            //determine which item to smelt
            Material minMat = in[minSmeltedIndex(in)];
            System.out.println("Smaller value is "+ minMat.getType()+", smelting now: "+ minMat.getType()+" ores: "+minMat.getRaw() +" "+ minMat.getType()+" Bars: "+minMat.getSmelted()+" Coal: "+coal);

            //get the amount of input to take. This is either 1, if the ratio is smaller than one, or the ratio itself
            int input = isInteger(minMat.getRatio()) ? (int)minMat.getRatio() : 1;
            //Retract the input
            minMat.setRaw(minMat.getRaw() - input);
            //Add the output
            int output = minMat.getSmelted() + (isInteger(minMat.getRatio()) ? 1 : (int)(1.0/minMat.getRatio()));
            minMat.setSmelted(output);

            coal--;
        }

        int[] output = new int[out.length];
        for (int i=0;i<out.length;i++){
            output[i]= out[i].getSmelted();
        }
        for (int i = 0; i<in.length; i++){
            in[i] = out[i];
        }
        return output;
    }

    public String findWeakestMaterialLink(Material[] mats, int coal){

        int[] output = smelt(mats, coal);

        System.out.println(Arrays.toString(mats));
        System.out.println(Arrays.toString(output));

        int minVar = min(min(output));
        HashMap<Integer, Material> map = new HashMap<Integer, Material>();
        for (int i = 0; i<output.length; i++){
            map.put(output[i],mats[i]);
        }

        Material weakestLink = map.get(min(output));

        return weakestLink.getType()+" Is the weakest link with "+weakestLink.getSmelted()+" Bars";
    }

    public int min(int ... nums) {
        int minimum = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < minimum) minimum = nums[i];
        }
        return minimum;
    }
    public int minIndex(int[] nums) {
        int minimum = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < minimum) minimum = i;
        }
        return minimum;
    }
    public int minRawIndex(Material[] mats){
        int minimum = 0;
        for(int i = 1; i < mats.length; i++){
            if(mats[i].getRaw() < mats[minimum].getRaw()) minimum = i;
        }
        return minimum;
    }
    public int minSmeltedIndex(Material[] mats){
        int minimum = 0;
        for(int i = 1; i < mats.length; i++){
            if(mats[i] == null) break;
            if(mats[i].getSmelted() < mats[minimum].getSmelted()) minimum = i;
        }
        return minimum;
    }

    public boolean isInteger(double value) {
        double differentValue = (int) value;
        return differentValue == value;
    }
}
