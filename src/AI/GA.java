/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Model.InputFromMySQL;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author KING
 */
public class GA {

    private final int crossoverProbability;    //Xac suat lai ghep
    private final int mutationProbability;     //Xac suat dot bien
    private final double desiredFitness;       //Fitness mong muon
    private final int popSize;                 //So luong ca the
    private Schedule bestOfPopulation;   //ca the tot nhat trong quan the hien tai
    private final Schedule bestOfBest;         //ca the tot nhat qua cac the he  
    private final ArrayList<Schedule> population;           //Quan the ban dau
    private final ArrayList<Schedule> nextPopulation;       //Quan the sau
    private ArrayList<Double> rulet;                           //Banh xe Rulet ung vi vi tri xac suat cua tung ca the trong quan the

    public GA(int crossoverProbability, int mutationProbability, double desiredFitness, int popSize) {
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;
        this.desiredFitness = desiredFitness;
        this.popSize = popSize;
        population = new ArrayList(popSize);
        nextPopulation = new ArrayList(popSize);
        for (int i = 0; i < popSize; i++) {
            nextPopulation.add(null);
        }
        bestOfPopulation = new Schedule();
        bestOfBest = new Schedule();
        rulet = new ArrayList();
    }

    public Schedule getBestOfPopulation() {
        return bestOfPopulation;
    }

    public Schedule getBestOfBest() {
        return bestOfBest;
    }

    public int getCrossoverProbability() {
        return crossoverProbability;
    }

    public int getMutationProbability() {
        return mutationProbability;
    }

    public int getPopSize() {
        return popSize;
    }

    //Ca the tot hon la ca the co Fitness lon hon hoac co Fitness bang nhau nhung softConflict nho hon
    //Tim bestSchedule trong population
    public void findBestOfPopulation() {
        bestOfPopulation = population.get(0);
        for (Schedule i : population) {
            if ((i.getFitness() > bestOfPopulation.getFitness()) || (i.getFitness() == bestOfPopulation.getFitness() && i.softConflict() < bestOfPopulation.softConflict())) {
                bestOfPopulation = i;
            }
        }
    }

    //Thay doi ca the tot nhat neu quan the xuat hien ca the tot hon qua cac the he
    public void findBestOfBest() {
        if ((bestOfPopulation.getFitness() > bestOfBest.getFitness()) || (bestOfPopulation.getFitness() == bestOfBest.getFitness() && bestOfPopulation.softConflict() < bestOfBest.softConflict())) {
            bestOfBest.setClasses(bestOfPopulation.getClasses());
            bestOfBest.setSlots(bestOfPopulation.getSlots());
            bestOfBest.setFitness(bestOfPopulation.getFitness());
            bestOfBest.setCriteria(bestOfPopulation.getCriteria());
        }
    }

    //Khoi tao quan the
    public void initializeThePopulation() {
        Schedule sc;
        for (int i = 0; i < popSize; i++) {
            sc = new Schedule();
            sc.initializeSchedule();
            population.add(sc);
        }
    }

    //Tao banh xe Rulet ung voi vi tri xac suat cua tung ca the trong quan the
    public void Rulet() {
        double total_fitness = 0;
        for (Schedule i : population) {
            total_fitness += i.getFitness();
        }
        //Xac suat chon cua tung ca the
        ArrayList<Double> probability_individual = new ArrayList();
        for (Schedule i : population) {
            probability_individual.add(i.getFitness() / total_fitness);
        }
        //Vi tri xac suat cua tung ca the
        ArrayList<Double> location_probability = new ArrayList();
        location_probability.add(probability_individual.get(0));
        for (int i = 1; i < popSize; i++) {
            location_probability.add(location_probability.get(i - 1) + probability_individual.get(i));
        }
        rulet = location_probability;
    }

    //Tai tao
    public void Reproduction() {

        //Quay banh xe Ru-let (1-XSDB)*popSize de lay ra so ca the tai tao
        int reproduction = (100 - crossoverProbability) * popSize / 100;
        Random rd = new Random();
        double x;
        for (int i = 0; i < reproduction; i++) {
            x = rd.nextDouble();
            if (x < rulet.get(0)) {
                nextPopulation.set(i, population.get(0)); //nextPop dung set vi trong qua trinh thay doi nextPop nhieu lan nen khong thee add dc
            } else if (x > rulet.get(popSize - 1)) {
                nextPopulation.set(i, population.get(popSize - 1));
            } else {
                for (int j = 0; j < popSize - 1; j++) {
                    if (x == rulet.get(j)) {
                        nextPopulation.set(i, population.get(j));
                    } else if (rulet.get(j) < x && x < rulet.get(j + 1)) {
                        nextPopulation.set(i, population.get(j + 1));
                    }
                }
            }
        }
    }

    //Lai ghep
    public void Crossover() {
        ArrayList<Schedule> cr = new ArrayList();
        int reproduction = (100 - crossoverProbability) * popSize / 100;
        int num_crossover = crossoverProbability * popSize / 100;
        Random rd = new Random();
        double x;
        for (int i = 0; i < num_crossover; i++) {
            x = rd.nextDouble();
            if (x < rulet.get(0)) {
                cr.add(population.get(0));
            } else if (x > rulet.get(popSize - 1)) {
                cr.add(population.get(popSize - 1));
            } else {
                for (int j = 0; j < popSize - 1; j++) {
                    if (x == rulet.get(j)) {
                        cr.add(population.get(j));
                    } else if (rulet.get(j) < x && x < rulet.get(j + 1)) {
                        cr.add(population.get(j + 1));
                    }
                }
            }
        }
        if (num_crossover % 2 == 0) {
            for (int i = 0; i < num_crossover; i += 2) {
                nextPopulation.set(reproduction + i, cr.get(i).Crossover(cr.get(i + 1)));
                nextPopulation.set(reproduction + i + 1, cr.get(i + 1).Crossover(cr.get(i)));
            }
        } else {
            for (int i = 0; i < num_crossover - 1; i += 2) {
                nextPopulation.set(reproduction + i, cr.get(i).Crossover(cr.get(i + 1)));
                nextPopulation.set(reproduction + i + 1, cr.get(i + 1).Crossover(cr.get(i)));
            }
            nextPopulation.set(popSize - 1, cr.get(num_crossover - 1));
        }
    }

    //Dot bien
    public void Mutation() {
        int num_mutation = mutationProbability * popSize / 100;
        Random rd = new Random();
        for (int i = 0; i < num_mutation; i++) {
            int x = rd.nextInt(popSize);
            nextPopulation.get(x).Mutation();
        }
    }

    public int runGA() {
        int generation = 1;
        //0. Tao quan the ban dau
        initializeThePopulation();

        do {
            System.out.print("Generation " + generation + ":  ");
            //1. Tim ca the tot nhat trong quan the hien tai va ca the tot nhat qua cac the he
            findBestOfPopulation();
            findBestOfBest();

            System.out.println(bestOfPopulation.getFitness());

            //2. Tao banh xe Rulet
            Rulet();

            //3. Tai tao
            Reproduction();

            //4.Lai ghep
            Crossover();

            //5. Dot bien
            Mutation();

            //Gan pop = nextPop
            for (int i = 0; i < popSize; i++) {
                population.set(i, nextPopulation.get(i));
            }
            generation++;
        } while (!((bestOfPopulation.getFitness() == desiredFitness) || generation == 10000)); 
//        while (!((bestOfPopulation.getFitness() == desiredFitness && bestOfPopulation.softConflict()==0 ) || generation == 10000)); 

        System.out.println("BestOfBest:" + bestOfBest.getFitness());
        //Kiem tra cac rang buoc cua tung lop cua bestOfBest qua criteria
        int x = 0;
        for (Boolean i : bestOfBest.getCriteria()) {
            if (i) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
            if ((x + 1) % 5 == 0) {
                System.out.print("   " + InputFromMySQL.getClassList().get(x / 5).getId() + "\n");
            }
            x++;
        }
        return generation;

    }

}
