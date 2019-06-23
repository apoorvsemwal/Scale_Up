using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MVVMDemo.Model;
using System.Collections.ObjectModel;

namespace MVVMDemo.ViewModel
{
	public class StudentViewModel
	{

		public ObservableCollection<Student> Students
		{
			get;
			set;
		}

		public void LoadStudents()
		{
			ObservableCollection<Student> students = new ObservableCollection<Student>();

			students.Add(new Student { FirstName = "Apoorv", LastName = "Semwal" });
			students.Add(new Student { FirstName = "Loveshant", LastName = "Grewal" });
			students.Add(new Student { FirstName = "Binay", LastName = "Kumar" });

			Students = students;
		}
	}
}
