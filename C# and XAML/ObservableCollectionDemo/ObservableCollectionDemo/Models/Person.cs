using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ObservableCollectionDemo.Models
{
	public class Person
	{
		private string _firstName;

		public string FirstName
		{
			get { return _firstName; }
			set
			{
				_firstName = value;
			}
		}

		private string _lastName;

		public string LastName
		{
			get { return _lastName; }
			set
			{
				_lastName = value;
			}
		}

		public string FullName
		{
			get { return string.Format("{0} {1}", _firstName, _lastName); }
		}

		private string _gender;

		public string Gender
		{
			get { return _gender; }
			set
			{
				_gender = value;
			}
		}

		public override string ToString()
		{
			return string.Format("{0} {1}", _firstName, _lastName);
		}

	}
}
