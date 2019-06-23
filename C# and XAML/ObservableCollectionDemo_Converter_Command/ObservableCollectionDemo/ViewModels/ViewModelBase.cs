using ObservableCollectionDemo.Models;
using ObservableCollectionDemo.ViewModels.Commands;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace ObservableCollectionDemo.ViewModels
{
	public class ViewModelBase
	{
		public ParameterizedCommand ParameterizedCommand { get; set; }

		private ObservableCollection<Person> _persons;

		public ObservableCollection<Person> Persons
		{
			get { return _persons; }
			set { _persons = value; }
		}

		public ViewModelBase()
		{
			Persons = new ObservableCollection<Person>();
			this.ParameterizedCommand = new ParameterizedCommand(new Action<Person>(ParameterizedMethod));
		}

		public void ParameterizedMethod(Person person)
		{
			Person oldPerson = Persons.SingleOrDefault(per => per.FirstName == person.FirstName && per.LastName == person.LastName
											 && per.Gender == person.Gender);
			if (oldPerson == null)
			{
				Person newPerson = new Person();
				newPerson.FirstName = person.FirstName;
				newPerson.LastName = person.LastName;
				newPerson.Gender = person.Gender;
				Persons.Add(newPerson);
				MessageBox.Show("New Person added!.");
			}
			else
			{
				MessageBox.Show("Duplicate Person cannot be added!.");
			}

			oldPerson = null;
		}

	}
}
