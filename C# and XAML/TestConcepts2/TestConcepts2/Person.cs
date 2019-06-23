using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestConcepts2
{
	public class Person : INotifyPropertyChanged
	{
		private string firstname;
		private string lastname;
		private string fullname;

		public string Firstname {
			get { return firstname; }
			set { firstname = value; onPropertyChanged("Firstname"); onPropertyChanged("Fullname"); }
		}

		public string Lastname {
			get { return lastname; }
			set { lastname = value; onPropertyChanged("Lastname"); onPropertyChanged("Fullname"); }
			}

		public string Fullname {
			get { return fullname = firstname + " " + lastname; }
			set { fullname = value; onPropertyChanged("Fullname"); }
		}

		public event PropertyChangedEventHandler PropertyChanged;

		private void onPropertyChanged(string property) {
			if (PropertyChanged != null) {
				PropertyChanged(this, new PropertyChangedEventArgs(property));
			}
		}
	}
}
