using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace TestCommands.ViewModel.Commands
{
	public class MessageCommand : ICommand
	{
		private Action<object> _execute;
		//readonly Predicate<object> _canExecute;

		public MessageCommand(Action<object> execute)
		{

			_execute = execute;
			//_canExecute = canExecute;
		}

		public event EventHandler CanExecuteChanged;
		//{
			//add { CommandManager.RequerySuggested += value; }
			//remove { CommandManager.RequerySuggested -= value; }
		//}

		public bool CanExecute(object parameter)
		{
			return true;
		}

		public void Execute(object parameter)
		{
			_execute.Invoke(parameter);
		}
	}
}
