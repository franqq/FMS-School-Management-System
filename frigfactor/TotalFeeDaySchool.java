/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frigfactor;

/**
 *
 * @author franq
 */
public class TotalFeeDaySchool {
    public static double getTotalFeeDayScholars(int classid, String fordetails)
    {
        //get residence fee
        double transportFee = TransportFee.getTransportFee(classid);
        double mealsFee = MealsFee.getMealsFee(classid, fordetails);
        OtherFeeDaySchoolars ofds = new OtherFeeDaySchoolars();
        double otherFee = ofds.calculateFeeDetails(classid);
        double totalFeeRequired = transportFee+mealsFee+otherFee;
        return totalFeeRequired;
    }
}
