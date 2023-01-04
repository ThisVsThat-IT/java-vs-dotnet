using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Microsoft.VisualBasic.FileIO;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class BirthController : Controller
    {
        private readonly BirthContext _context;

        public BirthController(BirthContext context)
        {
            _context = context;
        }

        [HttpPut(Name = "ParseAndGet")]
        public async Task<ActionResult<List<BirthModel>>> ParseAndGet() //Task<IActionResult>
        {
            List<BirthModel> BirthModels = readFile();
            CalculateAge(BirthModels);

            _context.BirthModels.AddRange(BirthModels);
            await _context.SaveChangesAsync();

            return _context.BirthModels != null ?
                Ok(await _context.BirthModels.ToListAsync()) :
                Problem("Entity set 'BirthContext.BirthModels'  is null.");

            //return Ok(BirthModels);
        }

        private void CalculateAge(List<BirthModel> current)
        {
            current.ForEach(delegate(BirthModel birth) {
                int age = DateOnly.FromDateTime(DateTime.Now).Year - birth.BirthDate.Year;
                birth.Age = age;
            });

        }

        private List<BirthModel> readFile()
        {
            List<BirthModel> BirthModels = new List<BirthModel>();

            using (TextFieldParser csvParser = new TextFieldParser("birthdates.csv"))
            {
                csvParser.CommentTokens = new string[] { "#" };
                csvParser.SetDelimiters(new string[] { "," });

                // Skip the row with the column names
                csvParser.ReadLine();

                while (!csvParser.EndOfData)
                {
                    // Read current line fields, pointer moves to the next line.
                    string[] fields = csvParser.ReadFields();
                    string FirstName = fields[0];
                    string LastName = fields[1];
                    DateOnly BirthDate = DateOnly.ParseExact(fields[2], "dd/MM/yyyy", CultureInfo.InvariantCulture);

                    BirthModels.Add(new BirthModel(FirstName, LastName, BirthDate));
                }
            }
            return BirthModels;
        }
    }
}
