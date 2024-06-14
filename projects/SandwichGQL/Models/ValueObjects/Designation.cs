using System.ComponentModel.DataAnnotations.Schema;
using System;
using System.Net.Mail;
using SandwichGQL.Shared;

namespace SandwichGQL.Models.ValueObjects
{
    [ComplexType]
    [GraphQLDescription("Represents the designation of a certain sandwich.")]
    public class Designation : IValueObject
    {


            public string designation {get;}

            public Designation(){}


            public Designation(string desi)
            {
                if(Verify(desi)) {
                    this.designation = desi;
                } else {
                    throw new BusinessRuleValidationException("The designation is empty.");
                }

            }

            private bool Verify(string desi)
            {
                return desi.Length>0;
            }
    }
}
