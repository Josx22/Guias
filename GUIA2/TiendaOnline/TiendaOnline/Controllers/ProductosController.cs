using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TiendaOnline.Models;

namespace TiendaOnline.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class ProductosController : ControllerBase
    {
        private readonly TiendaContext _context;
        public ProductosController(TiendaContext context)
        {
            _context = context;
        }
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Producto>>> GetProductos()
        {
            return await _context.Productos.Include(p => p.Categoria).ToListAsync();
        }
        [HttpGet("{id}")]
        public async Task<ActionResult<Producto>> GetProducto(int id)
        {
            var producto = await _context.Productos.Include(p => p.Categoria)
                                                   .FirstOrDefaultAsync(p => p.ProductoId == id);
            if (producto == null)
            {
                return NotFound();
            }
            return producto;
        }
        [HttpPatch("{id}")]
        public async Task<IActionResult> PatchProducto(int id, [FromBody] Producto producto)
        {
            var existingProduct = await _context.Productos.FindAsync(id);

            if (existingProduct == null) return NotFound();

            if (!string.IsNullOrEmpty(producto.Nombre))
            {
                existingProduct.Nombre = producto.Nombre;
            }

            if (producto.Precio > 0)
            {
                existingProduct.Precio = producto.Precio;
            }

            if (!string.IsNullOrEmpty(producto.Descripcion))
            {
                existingProduct.Descripcion = producto.Descripcion;
            }


            await _context.SaveChangesAsync();

            return Ok(existingProduct);
        }

        [HttpPost]
        public async Task<ActionResult<Producto>> PostProducto([FromBody]Producto producto)
        {
            _context.Productos.Add(producto);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetProducto), new { id = producto.ProductoId }, producto);
        }
        [HttpPut("{id}")]
        public async Task<IActionResult> PutProducto(int id, Producto producto)
        {
            if (id != producto.ProductoId) return BadRequest();

            _context.Entry(producto).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!_context.Productos.Any(e => e.ProductoId == id)) return NotFound();
                else throw;
            }

            return NoContent();
        }
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteProducto(int id)
        {
            var producto = await _context.Productos.FindAsync(id);
            if (producto == null) return NotFound();

            _context.Productos.Remove(producto);
            await _context.SaveChangesAsync();

            return NoContent();
        }
    }
}
