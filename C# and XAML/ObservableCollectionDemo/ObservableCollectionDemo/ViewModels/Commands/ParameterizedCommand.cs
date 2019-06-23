using ObservableCollectionDemo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace ObservableCollectionDemo.ViewModels.Commands
{
	public class ParameterizedCommand : ICommand
	{
		private Action<Person> _execute;

		public ParameterizedCommand(Action<Person> execute)
		{
			_execute = execute;
		}

		public event EventHandler CanExecuteChanged
		{
			add { CommandManager.RequerySuggested += value; }
			remove { CommandManager.RequerySuggested -= value; }
		}

		public bool CanExecute(object parameter)
		{
			Person person = parameter as Person;
			if (person != null)
			{
				if (String.IsNullOrWhiteSpace(person.FirstName) || String.IsNullOrWhiteSpace(person.Gender))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				return false;
			}
		}

		public void Execute(object parameter)
		{
			_execute(parameter as Person);
		}
	}
}
