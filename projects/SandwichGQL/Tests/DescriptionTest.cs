using SandwichGQL.Models.ValueObjects;
using SandwichGQL.Shared;
using Xunit;
using Assert = Xunit.Assert;

namespace SandwichGQL.Tests
{


    public class DescriptionTest
    {

        [Fact]
        public void TestDescriptionError()
        {
           Assert.Throws<BusinessRuleValidationException>(() => new Description(""));
        }


        [Fact]
        public void TestDescriptionValid()
        {
            Description sel = new Description("aaaa");
            Assert.Equal(sel.description,"aaaa");
        }   
    }
}