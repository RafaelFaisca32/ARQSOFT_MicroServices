using SandwichGQL.Data;
using SandwichGQL.Models;

namespace SandwichGQL.GraphQL{



    public class Query{
        [UseDbContext(typeof(AppDbContext))]
        public IQueryable<Sandwich> GetSandwich([ScopedService] AppDbContext context){
            return context.Sandwiches;
        }

    }
}
