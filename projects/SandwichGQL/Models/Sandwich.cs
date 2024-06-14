using System.ComponentModel.DataAnnotations;
using SandwichGQL.Models.ValueObjects;
using SandwichGQL.Shared;

namespace SandwichGQL.Models
{
    [GraphQLDescription("Represents a Sandwich that can be found in our stores with its designation, selling price and description.")]
    public class Sandwich : IAggregateRoot {

        
        [Key]
        public int id {get; set;}
        
        [Required]
        public Designation designation {get; set;}

        [Required]
        public SellingPrice sellingPrice {get; set;}

        [Required]
        public Description description {get; set;}

            public Sandwich()
        {
        }

        public Sandwich(Designation d , SellingPrice s , Description des){
            this.description = des;
            this.sellingPrice = s;
            this.designation = d;
        }

    }
}