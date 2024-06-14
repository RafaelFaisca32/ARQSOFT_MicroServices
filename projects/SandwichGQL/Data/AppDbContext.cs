using Microsoft.EntityFrameworkCore;
using SandwichGQL.Models;
using SandwichGQL.Models.ValueObjects;

namespace SandwichGQL.Data{

    public class AppDbContext : DbContext{
        public AppDbContext(DbContextOptions options) :base(options){

        }

         protected override void OnModelCreating(ModelBuilder modelBuilder)
        {

            modelBuilder.Entity<Sandwich>(entity =>
        {
            entity.HasKey(b => b.id);
            entity.OwnsOne(b => b.description, n =>
            {
                n.Property(b => b.description ).IsRequired();
            });
            entity.OwnsOne(b => b.designation, n =>
            {
                n.Property(b => b.designation ).IsRequired();
            });
            entity.OwnsOne(b => b.sellingPrice, n =>
            {
                n.Property(b => b.sellingPrice).IsRequired();
            });
        });
        }

        public DbSet<Sandwich> Sandwiches {get; set;}
    }

    

}