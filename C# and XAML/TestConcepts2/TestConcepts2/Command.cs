using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace TestConcepts2
{
	public class Command : ICommand
	{
		Action<object> executeMethod;
		Func<object, bool> canExecuteMethod;

		public Command(Action<object> executeMethod, Func<object, bool> canExecuteMethod)
		{
			this.executeMethod = executeMethod;
			this.canExecuteMethod = canExecuteMethod;
		}

		public bool CanExecute(object parameter)
		{
			return true;
		}

		public void Execute(object parameter)
		{
			executeMethod(parameter);
		}

		public event EventHandler CanExecuteChanged;
	}
}
