using SandwichGQL.Data;
using SandwichGQL.Models;
using SandwichGQL.SandwichMutation;

namespace SandwichGQL.GraphQL{



    public class Mutation{
        [UseDbContext(typeof(AppDbContext))]
        public async Task<AddSandwichPayload> AddSandwichAsync( AddSandwichInput input, [ScopedService] AppDbContext context){
           var sandwich = new Sandwich{
            designation = new Models.ValueObjects.Designation(input.designation),
            sellingPrice = new Models.ValueObjects.SellingPrice(input.sellingPrice),
            description = new Models.ValueObjects.Description(input.description)
           };
           context.Sandwiches.Add(sandwich);
           await context.SaveChangesAsync();

           return new AddSandwichPayload(sandwich);

        }

    }
}
