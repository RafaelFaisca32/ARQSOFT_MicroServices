
using SandwichGQL.Models;
using SandwichGQL.Models.ValueObjects;
using SandwichGQL.Shared;
using Xunit;
using Assert = Xunit.Assert;

namespace SandwichGQL.Tests
{


    public class SandwichTest
    {

        [Fact]
        public void TestSandwichCreateWithInvalidDescription()
        {
            Assert.Throws<BusinessRuleValidationException>(() => new Sandwich(new Designation("aaa"), new SellingPrice(22), new Description("")));
        }
        
        
        [Fact]
        public void TestSandwichCreateWithInvalidDesignation()
        {
            Assert.Throws<BusinessRuleValidationException>(() => new Sandwich(new Designation(""), new SellingPrice(22), new Description("aaaaaa")));
        }

        [Fact]
        public void TestSandwichCreateWithInvalidSellingPrice()
        {
            Assert.Throws<BusinessRuleValidationException>(() => new Sandwich(new Designation("sss"), new SellingPrice(-11), new Description("aaaaaa")));
        }

        [Fact]
        public void TestSandwichSuccess()
        {
            Sandwich sand =  new Sandwich(new Designation("sss"), new SellingPrice(11), new Description("aaaaaa"));
            Assert.Equal(sand.description.description, "aaaaaa");
            Assert.Equal(sand.designation.designation, "sss");
            Assert.Equal(sand.sellingPrice.sellingPrice, 11);
        }
        
        
    }
}