package kr.pe.gamgoon.hermetization.toby;

public class VisitorPattern {
	// 비지터
	interface CarElementVisitor {
		void visit(Wheel wheel);
		void visit(Engine engine);
		void visit(Body body);
		void visit(Car car);
	}
	// 대상
	interface CarElement {
		void accept(CarElementVisitor visitor); // CarElements have to provide accept()
	}
	
	// 대상 구현 클래스들
	static class Wheel implements CarElement {
		private String name;
		public Wheel(String name) {
			this.name = name;
		}
		public String getName() {
			return this.name;
		}
		@Override
		public void accept(CarElementVisitor visitor) {
			visitor.visit(this);
		}
	}
	
	static class Engine implements CarElement {

		@Override
		public void accept(CarElementVisitor visitor) {
			visitor.visit(this);
		}
	}
	
	static class Body implements CarElement {

		@Override
		public void accept(CarElementVisitor visitor) {
			visitor.visit(this);
		}
	}
	
	static class Car implements CarElement {
		CarElement[] elements;
		public CarElement[] getElements() {
			return elements.clone(); // Return a copy of the array of references.
		}
		public Car() {
			this.elements = new CarElement[] {
					new Wheel("front left"), new Wheel("front right"),
					new Wheel("back left"), new Wheel("back right"),
					new Body(), new Engine()
			};
		}
		@Override
		public void accept(CarElementVisitor visitor) {
			for (CarElement element : this.getElements()) {
				element.accept(visitor);
			}
			visitor.visit(this);
		}
	}
	///////////////////////////////////////
	
	// 방문자 구현 클래스들
	static class CarElementPrintVisitor implements CarElementVisitor {

		@Override
		public void visit(Wheel wheel) {
			System.out.println("Visiting " + wheel.getName() + " wheel");
		}

		@Override
		public void visit(Engine engine) {
			System.out.println("Visiting engine");
		}

		@Override
		public void visit(Body body) {
			System.out.println("Visiting body");
		}

		@Override
		public void visit(Car car) {
			System.out.println("Visiting car");
		}
	}
	
	static class CarElementDoVisitor implements CarElementVisitor {

		@Override
		public void visit(Wheel wheel) {
			System.out.println("Kicking my " + wheel.getName() + " wheel");
		}

		@Override
		public void visit(Engine engine) {
			System.out.println("Starting my engine");
		}

		@Override
		public void visit(Body body) {
			System.out.println("Moving my body");			
		}

		@Override
		public void visit(Car car) {
			System.out.println("Starting my car");			
		}
	}
	
	public static void main(String[] args) {
		Car car = new Car();
		car.accept(new CarElementPrintVisitor());
		car.accept(new CarElementDoVisitor());
	}
}
