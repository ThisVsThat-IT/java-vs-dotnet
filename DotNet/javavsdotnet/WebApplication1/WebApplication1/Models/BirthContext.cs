using Microsoft.EntityFrameworkCore;

namespace WebApplication1.Models
{
    public class BirthContext : DbContext
    {
        public BirthContext(DbContextOptions<BirthContext> options)
       : base(options)
        {
        }

        public DbSet<BirthModel> BirthModels { get; set; } = null!;
    }
}
