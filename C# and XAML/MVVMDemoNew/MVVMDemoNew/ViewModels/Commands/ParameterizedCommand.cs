using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace MVVMDemoNew.ViewModels.Commands
{
	public class ParameterizedCommand : ICommand
	{
		private Action<string> _execute;

		public ParameterizedCommand(Action<string> execute)
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
			string param = parameter as string;
			if (String.IsNullOrWhiteSpace(param))
			{
				return false;
			}
			else
			{
				return true;
			}

		}

		public void Execute(object parameter)
		{
			_execute(parameter as string);
		}
	}
}
