using Caliburn.Micro;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WPF_MVVM.Models;

namespace WPF_MVVM.ViewModels
{
	//public class ShellViewModel : Screen
    public class ShellViewModel : Conductor<object>
	{
		private string _firstname = "Apoorv";

		private string _lastname;

		private BindableCollection<PersonModel> _people = new BindableCollection<PersonModel>();

		private PersonModel _slectedPerson;

		public ShellViewModel()
		{
			People.Add(new PersonModel { FirstName = "Apoorv", LastName = "Semwal" });
			People.Add(new PersonModel { FirstName = "Binay", LastName = "Kumar" });
			People.Add(new PersonModel { FirstName = "Love", LastName = "Grewal" });
		}
		public string FirstName
		{
			get
			{
				return _firstname;
			}
			set
			{
				_firstname = value;
				NotifyOfPropertyChange(() => FirstName);
				NotifyOfPropertyChange(() => FullName);
			}
		}

		public string LastName
		{
			get
			{
				return _lastname;
			}
			set
			{
				_lastname = value;
				NotifyOfPropertyChange(() => LastName);
				NotifyOfPropertyChange(() => FullName);
			}
		}

		public string FullName
		{
			get
			{
				return $"{FirstName} {LastName}";
			}
		}

		public BindableCollection<PersonModel> People
		{
			get { return _people; }
			set { _people = value; }
		}

		public PersonModel SelectedPerson
		{
			get { return _slectedPerson; }
			set
			{
				_slectedPerson = value;
				NotifyOfPropertyChange(() => SelectedPerson);
			}
		}

		public bool CanClearText(string firstName, string lastName) => !String.IsNullOrWhiteSpace(firstName) || !String.IsNullOrWhiteSpace(lastName);
		//{
		//	return !String.IsNullOrWhiteSpace(firstName) || !String.IsNullOrWhiteSpace(lastName);
		//}

		public void ClearText(string firstName, string lastName)
		{
			FirstName = "";
			LastName = "";
		}

		public void LoadPageOne()
		{
			ActivateItem(new FirstChildViewModel());
		}

		public void LoadPageTwo()
		{
			ActivateItem(new SecondChildViewModel());
		}
	}
}
