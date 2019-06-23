using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MVVMDemoNew.Models
{
	public class Person : INotifyPropertyChanged
	{

		private string _firstName;

		public string FirstName
		{
			get { return _firstName; }
			set
			{
				_firstName = value;
				OnPropertyChanged("FullName");
			}
		}

		private string _lastName;

		public string LastName
		{
			get { return _lastName; }
			set
			{
				_lastName = value;
				OnPropertyChanged("FullName");
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
				OnPropertyChanged("Gender");
			}
		}

		public event PropertyChangedEventHandler PropertyChanged; //List of Event Subscribers.

		private void OnPropertyChanged(string propName)
		{
			if (PropertyChanged!=null) //Event has listeners.
			{
				PropertyChanged(this, new PropertyChangedEventArgs(propName)); //Calling Handler on each Subscriber.
			}
		}
	}
}
