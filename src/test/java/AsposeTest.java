import com.aspose.words.SaveFormat;
import com.cs.aspose.aspose.AsposeContext;
import com.cs.aspose.aspose.AsposeFactory;
import com.cs.aspose.aspose.MessageResource;
import com.cs.aspose.aspose.data.DocumentTemplate;
import com.cs.aspose.aspose.data.TemplateFile;
import com.cs.aspose.aspose.data.ImageValue;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Hoang DANG.
 */
public class AsposeTest {

    private DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
        .appendPattern("dd/MM/yyyy HH:mm")
        .toFormatter();

    @Test
    public void generateDocument() throws IOException {
        Hero hero = createHero();
        List<TemplateFile> templates = new ArrayList<>();
        templates.add(new TemplateFile("hero.docx"));
        templates.add(new TemplateFile("skill.docx", "skills"));

        byte[] data = getAsposeFactory().generateDocument(new DocumentTemplate<>(hero, templates, SaveFormat.PDF));

        File file = File.createTempFile("cs_aspose_word_test", Long.toString(new Date().getTime()) + ".pdf");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        }
        System.out.println(file);
    }

    private AsposeFactory getAsposeFactory() {
        AsposeFactory factory = new AsposeFactory(
            new AsposeContext(Locale.ENGLISH)
                .setMessageResource(new MessageResource() {
                    @Override
                    public String getMessage(Locale language, String key) {
                        return language + "_" + key;
                    }

                    @Override
                    public String getMessage(Locale language, String key, Object... params) {
                        return language + "_" + key;
                    }
                })
                .register(LocalDateTime.class, (ctx, originalValue, value) -> {
                    if (!(value instanceof LocalDateTime)) {
                        return value;
                    }
                    return DATE_TIME_FORMATTER.format((LocalDateTime) value);
                })
        );

        return factory;
    }

    private Hero createHero() {
        Hero hero = new Hero();
        hero.setName("Codsay");
        hero.setCreatedDate(LocalDateTime.now());
        hero.setAuto(new SpecificPower(new Skill("Simple Kick", 10), "Auto hit"));
        hero.getSkills().add(new Skill("Hit the sky", 25));
        hero.getSkills().add(new Skill("Trust me", 40));
        hero.getSkills().add(new Skill("Infinity black", 90));
        return hero;
    }

    public static class Hero {

        private String name;
        private LocalDateTime createdDate;
        private ImageValue avatar;

        private List<Skill> skills = new ArrayList<>();
        private SpecificPower active;
        private SpecificPower auto;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDateTime getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
        }

        public ImageValue getAvatar() {
            return avatar;
        }

        public void setAvatar(ImageValue avatar) {
            this.avatar = avatar;
        }

        public List<Skill> getSkills() {
            return skills;
        }

        public void setSkills(List<Skill> skills) {
            this.skills = skills;
        }

        public SpecificPower getActive() {
            return active;
        }

        public void setActive(SpecificPower active) {
            this.active = active;
        }

        public SpecificPower getAuto() {
            return auto;
        }

        public void setAuto(SpecificPower auto) {
            this.auto = auto;
        }
    }

    public static class Skill {

        private String name;
        private int power;

        public Skill(String name, int power) {
            this.name = name;
            this.power = power;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }
    }

    private class SpecificPower {

        private Skill skill;
        private String description;

        public SpecificPower(Skill skill, String description) {
            this.skill = skill;
            this.description = description;
        }

        public Skill getSkill() {
            return skill;
        }

        public void setSkill(Skill skill) {
            this.skill = skill;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
