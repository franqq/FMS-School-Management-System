/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frigfactor;

/**
 *
 * @author franq
 */
public class TotalFeeBorders {
    public static double getTotalFeeBorders(int residenceid,int classid, String fordetailsmeals)
    {
        //get residence fee
        double residenceFee = AccomodationFee.getAccomodationFee(residenceid);
        double mealsFee = MealsFee.getMealsFee(classid, fordetailsmeals);
        OtherFeeBorders ofb = new OtherFeeBorders();
        double otherFee = ofb.calculateFeeDetails(classid);
        double totalFeeRequired = residenceFee+mealsFee+otherFee;
        return totalFeeRequired;
    }
}
