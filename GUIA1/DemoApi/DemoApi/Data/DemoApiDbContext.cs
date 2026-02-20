using Microsoft.EntityFrameworkCore;
using DemoApi.Models;

namespace DemoApi.Data
{
    public class DemoApiDbContext : DbContext
    {
        public DemoApiDbContext(DbContextOptions<DemoApiDbContext> options) : base(options)
        {
        }
        public DbSet<Product> Products { get; set; }
    }
}
