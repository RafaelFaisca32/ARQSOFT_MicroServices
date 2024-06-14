using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace SandwichGQL.Migrations
{
    /// <inheritdoc />
    public partial class AddSandwichToDB : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Sandwiches",
                columns: table => new
                {
                    id = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    designationdesignation = table.Column<string>(name: "designation_designation", type: "nvarchar(max)", nullable: false),
                    sellingPricesellingPrice = table.Column<int>(name: "sellingPrice_sellingPrice", type: "int", nullable: false),
                    descriptiondescription = table.Column<string>(name: "description_description", type: "nvarchar(max)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Sandwiches", x => x.id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Sandwiches");
        }
    }
}
