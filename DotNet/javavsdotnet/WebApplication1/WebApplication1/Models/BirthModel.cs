namespace WebApplication1.Models
{
    public class BirthModel
    {
        public BirthModel()
        {
        }
        public BirthModel(string first, string last, DateOnly birth)
        {
            FirstName = first;
            LastName = last;
            BirthDate = birth;
        }
        public long Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public DateOnly BirthDate { get; set; }
        public int? Age { get; set; }
    }
}
