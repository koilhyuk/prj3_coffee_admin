package kr.co.sist.team2.vo;

public class MenuNewRecipeVO {
	private int recipeShot, recipeCream;
	private String insertRecipeGdName, recipeMilk, recipeSyrup, recipeTopping, recipeType;
	public int getRecipeShot() {
		return recipeShot;
	}
	public void setRecipeShot(int recipeShot) {
		this.recipeShot = recipeShot;
	}
	public int getRecipeCream() {
		return recipeCream;
	}
	public void setRecipeCream(int recipeCream) {
		this.recipeCream = recipeCream;
	}
	public String getInsertRecipeGdName() {
		return insertRecipeGdName;
	}
	public void setInsertRecipeGdName(String insertRecipeGdName) {
		this.insertRecipeGdName = insertRecipeGdName;
	}
	public String getRecipeMilk() {
		return recipeMilk;
	}
	public void setRecipeMilk(String recipeMilk) {
		this.recipeMilk = recipeMilk;
	}
	public String getRecipeSyrup() {
		return recipeSyrup;
	}
	public void setRecipeSyrup(String recipeSyrup) {
		this.recipeSyrup = recipeSyrup;
	}
	public String getRecipeTopping() {
		return recipeTopping;
	}
	public void setRecipeTopping(String recipeTopping) {
		this.recipeTopping = recipeTopping;
	}
	public String getRecipeType() {
		return recipeType;
	}
	public void setRecipeType(String recipeType) {
		this.recipeType = recipeType;
	}
	@Override
	public String toString() {
		return "MenuNewRecipeVO [recipeShot=" + recipeShot + ", recipeCream=" + recipeCream + ", insertRecipeGdName="
				+ insertRecipeGdName + ", recipeMilk=" + recipeMilk + ", recipeSyrup=" + recipeSyrup
				+ ", recipeTopping=" + recipeTopping + ", recipeType=" + recipeType + "]";
	}

}
