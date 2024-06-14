using System.ComponentModel.DataAnnotations.Schema;
using System;
using System.Net.Mail;
using SandwichGQL.Shared;

namespace SandwichGQL.Models.ValueObjects
{
    [ComplexType]
    [GraphQLDescription("Represents the selling price of a certain sandwich.")]
    public class SellingPrice : IValueObject
    {


            public int sellingPrice {get;}

            public SellingPrice(){

            }


            public SellingPrice(int sel)
            {
                if(Verify(sel)) {
                    this.sellingPrice = sel;
                } else {
                    throw new BusinessRuleValidationException("The number you selected for the selling price is not accepted.");
                }

            }

            private bool Verify(int sel)
            {
                return sel>0;
            }
    }
}
