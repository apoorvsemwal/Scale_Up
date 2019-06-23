using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace TestCommands.ViewModel.Commands
{
	public class RelayCommand : ICommand
	{

		private Action<object> _execute;
		private Predicate<object> _canExecute;

		public RelayCommand(Action<object> execute, Predicate<object> canExecute)
		{
			_execute = execute;
			_canExecute = canExecute;
		}

		public RelayCommand(Action<object> execute): this(execute, null) //Calling Base Constructor above
		{
			
		}

		public event EventHandler CanExecuteChanged
		{
			add { CommandManager.RequerySuggested += value; }
			remove { CommandManager.RequerySuggested -= value; }
		}

		public bool CanExecute(object parameter)
		{
			return _canExecute == null ? true : _canExecute.Invoke(parameter);
			
		}

		public void Execute(object parameter)
		{
			_execute.Invoke(parameter);
		}
	}
}
