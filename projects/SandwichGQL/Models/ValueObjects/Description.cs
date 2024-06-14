using System.ComponentModel.DataAnnotations.Schema;
using SandwichGQL.Shared;

namespace SandwichGQL.Models.ValueObjects
{
    [ComplexType]
    [GraphQLDescription("Represents the description of a certain sandwich.")]
    public class Description : IValueObject
    {


            public string description {get;}
            public Description(){
                
            }


            public Description(string desi)
            {
                if(Verify(desi)) {
                    this.description = desi;
                } else {
                    throw new BusinessRuleValidationException("The description is empty.");
                }

            }

            

            private bool Verify(string desi)
            {
                return desi.Length>0;
            }
    }
}